export class SessionSbService {
    RESOURCES_URL;              // the back-end base url for authentication resources
    BROWSER_STORAGE_ITEM_NAME;  // the key into browser storage for retaining the token and account
    _currentToken;              // the current authentication token of this session
                                // to be injected in the authorization header of every outgoing request
    _currentAccount;            // the account instant of the currently logged on user

    constructor(resourcesUrl, browserStorageItemName) {
        console.log("Created SessionService...");
        this.BROWSER_STORAGE_ITEM_NAME = browserStorageItemName;
        this.RESOURCES_URL = resourcesUrl;
        this.getTokenFromBrowserStorage();

    }

    loadTokenFromLocalStorage() {
        const localStorageToken = localStorage.getItem(this.BROWSER_STORAGE_ITEM_NAME);
        if (localStorageToken) {
            this._currentToken = localStorageToken;
        }
    }


    async asyncSignIn(email, password) {
        let url
        let body
        try {
            url = this.RESOURCES_URL
            body = JSON.stringify({
                email: email,
                password: password
            })
            const response = await fetch(url, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: body,
                credentials: 'include',
            });

            if (response.ok) {
                const token = response.headers.get('Authorization');
                let user = await response.json();
                this.saveTokenIntoBrowserStorage(token, user);
                return true;
            } else {
                if (response.status === 401) {
                    console.error('Unauthorized. Token may have expired or invalid.');
                } else {
                    console.error('Error in response: ', response);
                }
                return false;
            }
        } catch (error) {
            console.error(error);
        }
    }

    signOut() {
        this.saveTokenIntoBrowserStorage(null, null);
    }

    saveTokenIntoBrowserStorage(token, user) {
        this._currentToken = token;
        this._currentAccount = user;

        try {
            if (token == null) {
                this._currentAccount = null;
                window.sessionStorage.removeItem('TOKEN');
                window.sessionStorage.removeItem('ACCOUNT');

                if (localStorage !== sessionStorage) {
                    window.localStorage.removeItem('TOKEN');
                    window.localStorage.removeItem('ACCOUNT');
                }
            } else {
                // insert into sessionStorage
                window.sessionStorage.setItem('TOKEN', token);
                window.sessionStorage.setItem('ACCOUNT', JSON.stringify(user));

                // insert into localStorage
                window.localStorage.setItem('TOKEN', token);
                window.localStorage.setItem('ACCOUNT', JSON.stringify(user));
            }
        } catch (e) {
            console.error('SessionStorage is not available, using LocalStorage instead.');
        }
    }

    getTokenFromBrowserStorage() {
        if (this._currentToken !== null) {
            return this._currentToken;
        }

        this._currentToken = window.sessionStorage.getItem(this.BROWSER_STORAGE_ITEM_NAME);
        const jsonAccount = window.sessionStorage.getItem(this.BROWSER_STORAGE_ITEM_NAME + "_ACC");

        if (this._currentToken == null) {
            this.loadTokenFromLocalStorage();
        }

        if (jsonAccount !== null) {
            this._currentAccount = JSON.parse(jsonAccount);
        }

        return this._currentToken;
    }

    isAuthenticated () {
        return this._currentAccount != null
    }

    get currentToken () {
        return this._currentToken
    }

    getUserName () {
        return this._currentAccount ? this._currentAccount.name : ''
    }
}
