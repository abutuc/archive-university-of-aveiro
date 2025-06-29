import axios from "axios";
const baseURL = "http://13.80.159.172/growmate"

export const getTodoTasks = async (userId) => {
    const response = await axios.get(baseURL + "/user/tasks/" + userId + "/todo");
    return response.data;
}
