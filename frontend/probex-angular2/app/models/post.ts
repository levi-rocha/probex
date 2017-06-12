import { User } from "./user";

export class Post {
    constructor(
        public id?: number,
        public title?: string,
        public content?: string,
        public author?: User,
        public authorUsername?: string,
        public contentPreview?: string,
        public voteCount?: number,
        public comments?: Comment[]
    ){}
}