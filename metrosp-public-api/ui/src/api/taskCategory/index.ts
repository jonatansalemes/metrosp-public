import endpoint from "../endpoint";
import Fetch from "../fetcher";
import { TaskCategory } from "../model";
import { resolveResponse } from "../shared";

const apiBaseUrl = endpoint.API_BASE_URL;

const all = async (): Promise<TaskCategory[]> => {
    const response = await Fetch.newInstance().path(apiBaseUrl)
        .path("taskCategories").get();
    return resolveResponse<TaskCategory[]>(response);
}

const taskCategory = {
    all
}

export default taskCategory;