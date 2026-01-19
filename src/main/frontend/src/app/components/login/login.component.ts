import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  imports: [],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  constructor(private authService: AuthService) {
  }

  public handleLogin(email: string, username: string): void {
    this.authService.login(email, username).subscribe(data => console.log(data));
  }
}
