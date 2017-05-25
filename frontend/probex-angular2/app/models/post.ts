import { User } from "./user";

export class Post {
    constructor(
        public id?: number,
        public title?: string,
        public content?: string,
        public userName?: User,
        public voteIds?: number[]
    ){}
}