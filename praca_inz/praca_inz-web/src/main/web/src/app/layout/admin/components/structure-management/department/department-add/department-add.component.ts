import {Component, Input, OnInit} from '@angular/core';
import {StructureAddElement} from "../../../../../../models/structure-add-element";
import {CompanyService} from "../../../administration/company/company.service";
import {Router} from "@angular/router";
import {StructureListElement} from "../../../../../../models/structure-list-element";
import {DepartmentService} from "../department.service";

@Component({
  selector: 'app-department-add',
  templateUrl: './department-add.component.html',
  styleUrls: ['./department-add.component.scss']
})
export class DepartmentAddComponent implements OnInit {

    @Input() structureAddElement: StructureAddElement= new StructureAddElement;

    companies: StructureListElement[];

    constructor(private companyService:CompanyService,private departmentService:DepartmentService,private router:Router) {
    }

    ngOnInit() {
        this.getCompanies();
    }

    getCompanies(){
        this.companyService.getAll().subscribe(companyListElement=> {this.companies=companyListElement});
    }

    departmentAdd(){
        this.departmentService.createDepartment(this.structureAddElement);
        this.router.navigate(['/admin/departments']);
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
