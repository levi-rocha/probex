import { RouterConfig } from '@angular/router'; 
import { UserListComponent } from './user-list-component';
import { UserSignupComponent } from './user-signup-component';
import { UserEditComponent } from './user-edit-component';
import { UserViewComponent } from './user-view-component';
import { LoginGuard } from '../../login-guard';

export const AlunosRoutes: RouterConfig = [
	{ 
		path: 'user-list', 
		component: UserListComponent, 
		canActivate: [ LoginGuard ] 
	}, 
	{ 
		path: 'user-signup', 
		component: UserSignupComponent, 
		canActivate: [ LoginGuard ] 
	}, 
	{ 
		path: 'user-edit/:id', 
		component: UserEditComponent, 
		canActivate: [ LoginGuard ] 
	},
	{ 
		path: 'user-view/:id', 
		component: UserViewComponent, 
		canActivate: [ LoginGuard ] 
	},
	{ 
		path: '', 
		redirectTo: '/user-list', 
		terminal: true 
	}
];