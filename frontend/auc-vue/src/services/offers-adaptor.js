export class OffersAdaptor {
    resourcesUrl;

    constructor(resourcesUrl) {
        this.resourcesUrl = resourcesUrl;
        console.log("Create Offers Adaptor for " + resourcesUrl);
    }

    async fetchJson(url, options = null) {
        let response =  await fetch(url, options);
        if (response.ok) {
            return await response.json();
        } else {
            console.log(response, !response.bodyUsed ? await response.text() : "");
            return null;
        }
    }

    async asyncFindAll() {
        const url = `${this.resourcesUrl}/offers/all`;
        return this.fetchJson(url);
    }

    async asyncFindById(id) {
        const url = `${this.resourcesUrl}/offers/${id}`;
        return this.fetchJson(url);
    }

    async asyncSave(offer) {
        let url;
        let method;

        if (offer.id === 0) {
            url = `${this.resourcesUrl}/offers`;
            method = 'POST';
        } else {
            url = `${this.resourcesUrl}/offers/${offer.id}`;
            method = 'PUT';
        }

        const options = {
            method,
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(offer),
        };

        return this.fetchJson(url, options);
    }

    async asyncDeleteById(id) {
        const url = `${this.resourcesUrl}/offers/${id}`;
        const options = {
            method: 'DELETE',
        };

        return this.fetchJson(url, options);
    }
}
