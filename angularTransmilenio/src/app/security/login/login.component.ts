import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginDto } from '../../dto/login-dto';
import { AuthService } from '../../shared/auth.service';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-login',
  imports: [FormsModule],
  standalone:true,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginDto: LoginDto = new LoginDto("", "");

  constructor(private auth: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.auth.logout();
  }

  login() {
    this.auth.login(this.loginDto)
      .subscribe({
        next: jwt => {
          console.log(jwt);
          this.router.navigate(["cuenta"]);
        },
        error: err => { console.error("Login failed:", err) }
      });
  }

}
