
import { Injectable } from '@angular/core';

const TOKEN = 'token';
const USER = 'user';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() { }

  static getUserId(): string {
    const user= this.getUser();
    if(user == null) return '';
    return user.id;
  }

  static saveToken(token: string): void {
    typeof localStorage !== 'undefined' && window.localStorage.setItem(TOKEN, token);
  }

  static saveUser(user: any): void {
    typeof localStorage !== 'undefined' && window.localStorage.setItem(USER, JSON.stringify(user));
  }

  static getToken(): string {
    return typeof localStorage !== 'undefined' ? localStorage.getItem(TOKEN) : null;
  }

  static getUser(): any {
    return typeof localStorage !== 'undefined' ? JSON.parse(localStorage.getItem(USER)) : null;
  }

  static getUserRole(): string {
    const user = this.getUser();
    return user ? user.role : '';
  }

  static isAdminLoggedIn(): boolean {
    return this.getToken() !== null && this.getUserRole() === "ADMIN";
  }

  static isCustomerLoggedIn(): boolean {
    return this.getToken() !== null && this.getUserRole() === "CUSTOMER";
  }

  static signout(): void {
    typeof localStorage !== 'undefined' && (window.localStorage.removeItem(USER), window.localStorage.removeItem(TOKEN));
  }

}




