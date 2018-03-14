import { Component, OnInit, Input } from '@angular/core';
import { AboutService } from '../service/about.service'
@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {
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
  comentaire = [{ date: null, message: "" }]


  constructor(private aboutservice: AboutService) {
    console.log(this.info);

  }
  ngOnInit() {
  }

  onAddComentaire(c) {
    c.date = new Date();
    this.comen.push(c);
    c.comentaire = "";
  }

}
