import {Component, Input, OnInit} from '@angular/core';
import {CompanyService} from "../company.service";
import {Router} from "@angular/router";
import {StructureAddElement} from "../../../../../../models/structure-elements";

@Component({
  selector: 'app-company-edit',
  templateUrl: './company-edit.component.html',
  styleUrls: ['./company-edit.component.scss']
})
export class CompanyEditComponent implements OnInit {

    @Input() structureAddElement: StructureAddElement = new StructureAddElement;

    constructor(private companyService:CompanyService,private router:Router) {
    }

    ngOnInit() {
    }


    companyAdd(){
        this.companyService.createCompany(this.structureAddElement);
        this.router.navigate(['/admin/companies']);
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
