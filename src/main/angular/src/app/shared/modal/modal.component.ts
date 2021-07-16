import {Component, OnInit} from '@angular/core';
import {BsModalRef} from "ngx-bootstrap/modal";
import {Subject} from "rxjs";

@Component({
  selector: 'app-dialog',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css']
})
export class ModalComponent implements OnInit {

  public body: string;
  public active: boolean;
  public onClose: Subject<boolean>;

  constructor(private modalRef: BsModalRef) { }

  ngOnInit(): void {
    this.onClose = new Subject();
  }

  public show(body){
    this.body = body;
    this.active = true;
  }

  confirm(){
    this.active = false;
    this.onClose.next(true);
    this.modalRef.hide();
  }

  cancel(){
    this.active = false;
    this.onClose.next(false);
    this.modalRef.hide()
  }
}
