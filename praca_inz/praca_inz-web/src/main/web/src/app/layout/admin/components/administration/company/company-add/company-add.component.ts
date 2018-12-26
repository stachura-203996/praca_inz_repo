import {Component, Input, OnInit} from '@angular/core';
import {StructureAddElement} from "../../../../../../models/structure-add-element";
import {CompanyService} from "../company.service";
import {Router} from "@angular/router";

@Component({
    selector: 'app-company-add',
    templateUrl: './company-add.component.html',
    styleUrls: ['./company-add.component.scss']
})
export class CompanyAddComponent implements OnInit {

    @Input() structureAddElement: StructureAddElement= new StructureAddElement;

    constructor(private companyService:CompanyService,private router:Router) {
    }

    ngOnInit() {
    }


    companyAdd(){
        this.companyService.createCompany(this.structureAddElement);
        // this.router.navigate(['/admin/companies']);
    }
    clear() {
      this.structureAddElement.description=null;
      this.structureAddElement.zipCode=null;
      this.structureAddElement.buildingNumber=null;
      this.structureAddElement.city=null;
      this.structureAddElement.flatNumber=null;
      this.structureAddElement.street=null;
      this.structureAddElement.name=null;
    }
}
