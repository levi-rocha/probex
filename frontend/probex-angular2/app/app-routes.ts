import { RouterModule, Routes } from '@angular/router';
import { UserRoutes } from './components/users/user-routes';
import { SigninRoutes } from './components/auth/signin-routes';
import { PostRoutes } from './components/post-routes';
import { AUTH_PROVIDERS } from './components/auth/signin-routes';

export const APP_ROUTES: Routes = [
	...UserRoutes,
	...SigninRoutes,
	...PostRoutes
];