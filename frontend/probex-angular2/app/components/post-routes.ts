import { RouterConfig } from '@angular/router'; 
import { NewPostComponent } from './new-post-component';
import { PostListComponent } from './post-list-component';
import { AuthGuard } from '../auth-guard';

export const PostRoutes: RouterConfig = [
	{ 
		path: 'new-post', 
		component: NewPostComponent, 
		canActivate: [ AuthGuard ] 
	},
    {
        path: 'post-list',
        component: PostListComponent,
        canActivate: [ AuthGuard ]
    },
	{ 
		path: '', 
		redirectTo: '/post-list', 
		terminal: true 
	}
];