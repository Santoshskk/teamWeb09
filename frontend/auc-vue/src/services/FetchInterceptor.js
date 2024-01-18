import FetchIntercept from 'fetch-intercept';

export class FetchInterceptor {
    static theInstance;
    sessionService;
    router;
    unregister;

    // Constructor for initializing the interceptor
    constructor(sessionService, router) {
        // Set the singleton instance to this
        FetchInterceptor.theInstance = this;
        // Assign session service and router
        this.sessionService = sessionService;
        this.router = router;
        // Set the singleton instance again (may be redundant)
        FetchInterceptor.theInstance = this;
        // Register the interceptor and get the unregister function
        this.unregister = FetchIntercept.register(this);
    }

    // Intercept the request and add authorization token if available
    request(url, options) {
        const token = FetchInterceptor.theInstance.sessionService.currentToken;
        if (token == null) {
            return [url, options];
        } else if (options == null) {
            // Token exists, but no options, add Authorization header to new options
            return [url, { headers: { Authorization: token } }];
        } else {
            // Token and options both exist, clone options and add Authorization header
            const newOptions = { ...options };
            newOptions.headers = {
                ...options.headers,
                Authorization: `Bearer ${token}`,
            };
            return [url, newOptions];
        }
    }

    // Handle request errors
    requestError(error) {
        return Promise.reject(error);
    }

    // Handle response errors
    responseError(error) {
        return console.log('FetchInterceptor responseError: ', error);
    }

    // Handle successful responses
    response(response) {
        try {
            // Check for HTTP status codes indicating an error
            if (response.status >= 400 && response.status < 600) {
                FetchInterceptor.theInstance.handleErrorInResponse(response);
            }
            return response;
        } catch (e) {
            console.error('FetchInterceptor response: ', e);
        }
    }

    // Handle specific errors in the response (e.g., unauthorized)
    handleErrorInResponse(response) {
        try {
            if (response.status === 401) {
                console.log('Unauthorized. Token may have expired or be invalid.');
                this.router.push({ path: '/sign-out' });
            } else {
                console.log('Unexpected error during login.');
            }
            this.responseError(response);
        } catch (e) {
            console.error('FetchInterceptor handleErrorInResponse: ', e);
        }
    }
}
