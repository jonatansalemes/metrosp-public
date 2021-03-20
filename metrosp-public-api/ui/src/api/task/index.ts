import endpoint from "../endpoint";
import Fetch from "../fetcher";
import { Task, TaskCategory } from "../model";
import { resolveResponse } from "../shared";

const apiBaseUrl = endpoint.API_BASE_URL;

const allForCategory = async (taskCategory: Pick<TaskCategory, "alias">): Promise<Task[]> => {
    const response = await Fetch.newInstance().path(apiBaseUrl)
        .path("tasks").queryString("taskCategory", taskCategory.alias).get();
    return resolveResponse<Task[]>(response);
}

const task = {
    allForCategory
}

export default task;