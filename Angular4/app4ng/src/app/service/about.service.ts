import { Injectable } from '@angular/core';

@Injectable()
export class AboutService {
  private info = {
    nom: "amine",
    adresse: "marseille",
    tel: 2552365521
  }
  comen = [
    { date: new Date(), message: "A" },
    { date: new Date(), message: "B" },
    { date: new Date(), message: "C" }
  ]
  addComent(c) {
    this.comen.push(c);
  }
  getAllComent() {
    return this.comen;
  }
  getInfo() {
    return this.info;
  }
  constructor() { }

}
