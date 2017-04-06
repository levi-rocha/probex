import { provideRouter, RouterConfig } from '@angular/router'; 
import { UserRoutes } from './components/users/user-routes';
import { SigninRoutes } from './components/auth/signin-routes';
import { AUTH_PROVIDERS } from './components/auth/signin-routes';

export const routes: RouterConfig = [
	...UserRoutes,
	...SigninRoutes
]; 

export const APP_ROUTER_PROVIDERS = [ 
	provideRouter(routes),
	AUTH_PROVIDERS
];