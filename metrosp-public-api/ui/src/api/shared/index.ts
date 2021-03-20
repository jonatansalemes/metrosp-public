import messages from "../../locale/message";

export type NotFoundResponse = {
    message: string;
}

export type ServiceUnavailableErrorResponse = {
    message: string;
}

export type ErrorResponse = {
    reason: string;
}

export const resolveResponse = async<T>(response: Response,
    onNotFound?: () => Promise<any>) => {
    if (response.status === 200) {
        return await response.json() as T;
    } else if (response.status === 404) {
        if (onNotFound) return onNotFound();
        return notFound(response);
    } else if (response.status === 503) {
        return serviceUnavailable(response);
    }
    return statusCodeNotExpected(response);
}

const notFound = async (response: Response): Promise<ErrorResponse> => {
    const notFoundResponse = await response.json() as NotFoundResponse;
    const reason = messages.format(notFoundResponse.message);
    return Promise.reject<ErrorResponse>({
        reason
    });
}

const statusCodeNotExpected = async (response: Response): Promise<ErrorResponse> => {
    const reason = messages.format('Status code {0} not expected', response.status);
    return Promise.reject<ErrorResponse>({
        reason
    });
}

const serviceUnavailable = async (response: Response): Promise<ErrorResponse> => {
    const serviceUnavailableErrorResponse = await response.json() as ServiceUnavailableErrorResponse;
    const reason = messages.format(serviceUnavailableErrorResponse.message);
    return Promise.reject<ErrorResponse>({
        reason
    });
}
