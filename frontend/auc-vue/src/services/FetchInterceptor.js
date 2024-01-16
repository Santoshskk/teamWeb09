import FetchIntercept from 'fetch-intercept'

export class FetchInterceptor {
    static theInstance
    sessionService
    router
    unregister

    constructor (sessionService, router) {
        FetchInterceptor.theInstance = this // because fetchIntercept hooks use old school ‘this’...
        this.sessionService = sessionService
        this.router = router
        FetchInterceptor.theInstance = this
        this.unregister = FetchIntercept.register(this)

        // console.log("FetchInterceptor has been registered; current token = ", FetchInterceptor.theInstance.sessionService.currentToken);
    }

    request (url, options) {
        const token = FetchInterceptor.theInstance.sessionService.currentToken
        if (token == null) {
            return [url, options]
        } else if (options == null) {
            return [url, { headers: { Authorization: token } }]
        } else {
            const newOptions = { ...options }
            newOptions.headers = {
                ...options.headers,
                Authorization: `Bearer ${token}`
            }
            return [url, newOptions]
        }
    }

    requestError (error) {
        return Promise.reject(error)
    }

    responseError (error) {
        // return Promise.reject(error);
        return console.log('FetchInterceptor responseError: ', error)
    }

    response (response) {
        // console.log('FetchInterceptor response: ', response)
        try {
            if (response.status >= 400 && response.status < 600) {
                FetchInterceptor.theInstance.handleErrorInResponse(response)
            }
            return response
        } catch (e) {
            console.error('FetchInterceptor response: ', e)
        }
    }

    handleErrorInResponse (response) {
        try {
            if (response.status === 401) {
                console.log('Unauthorized. Token may have expired or invalid.')
                this.router.push({ path: '/sign-out' })
            } else {
                console.log('Unexpected error during login.')
            }
            this.responseError(response)
        } catch (e) {
            console.error('FetchInterceptor handleErrorInResponse: ', e)
        }
    }
}
