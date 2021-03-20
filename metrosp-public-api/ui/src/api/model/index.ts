export type Task = {
    uuid: string;
    requester: string;
    content: string;
    createdAt: Date;
}

export type TaskCategory = {
    id: number;
    alias: string;
    name: string;
}