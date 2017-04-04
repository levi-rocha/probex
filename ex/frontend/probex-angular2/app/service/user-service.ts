import {Injectable} from 'angular2/core';
import {User} from '../model/user';

@Injectable()
export class UserService {

    private users: User[] = [
        new User('fulano', 'fulano123', 'fulano@email.com'),
        new User('beltrano', 'beltrano123', 'beltrano@email.com')
    ];

    listAll() {
        return this.users;
    }

    insert(user: User) {
        this.users.push(user);
    }

    update(id: number, user: User) {
        this.users[id] = user;
    }

    delete(id: number) {
        this.users.splice(id, 1);
    }

}