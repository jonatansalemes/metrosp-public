
export class UriFragments {

    constructor(private fragments: string[]) {
        this.fragments = fragments;
    }

    public end() {
        return this.fragments[this.fragments.length - 1];
    }
}

export default class Fetch {

    private paths: string[] = [];
    private queryStrings: string[] = [];
    private headers: Map<string, string> = new Map();

    private constructor() {
        this.headers.set('Content-Type', 'application/json');
    }

    public static fragments(uri: string): UriFragments {
        return new UriFragments(uri.split("/"));
    }


    public static newInstance() {
        return new Fetch();
    }

    public path(path: string): Fetch {
        this.paths.push(path);
        return this;
    }

    public resolveTemplate(template: any): Fetch {
        this.paths = this.paths.map(path => {
            if (path.startsWith(':')) {
                return template[path.replace(':', '')];
            }
            return path;
        });
        return this;
    }

    public queryString(name: string, value: string): Fetch {
        this.queryStrings.push(name + '=' + encodeURI(value));
        return this;
    }

    public get(): Promise<Response> {
        const request = {
            method: 'GET',
            headers: this.asFetchHeaders()
        };
        return this.fetch(request);
    }

    public put(body: any): Promise<Response> {
        const request = {
            method: 'PUT',
            headers: this.asFetchHeaders(),
            body: JSON.stringify(body)
        };
        return this.fetch(request);
    }

    public post(body: any): Promise<Response> {
        const request = {
            method: 'POST',
            headers: this.asFetchHeaders(),
            body: JSON.stringify(body)
        };
        return this.fetch(request);
    }

    public authorization(value: string): Fetch {
        return this.header("Authorization", "Bearer " + value);
    }

    public header(name: string, value: string): Fetch {
        this.headers.set(name, value);
        return this;
    }

    private fetch(request: any): Promise<Response> {
        return fetch(this.url(), request);
    }

    private asFetchHeaders(): Record<string, string> {
        let headers: Record<string, string> = {}
        for (let entry of Array.from(this.headers.entries())) {
            headers[entry[0]] = entry[1];
        }
        return headers;
    }

    private url(): string {
        return this.pathForUrl() + this.queryStringForUrl();
    }

    private pathForUrl() {
        return this.paths.join("/");
    }

    private queryStringForUrl() {
        return (this.queryStrings.length > 0 ? '?' + this.queryStrings.join('&') : '');
    }

}