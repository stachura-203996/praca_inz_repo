import {Component, Input, OnInit} from '@angular/core';
import {StructureAddElement} from "../../../../../../models/structure-add-element";
import {CompanyService} from "../../../administration/company/company.service";
import {Router} from "@angular/router";
import {OfficeService} from "../office.service";
import {StructureListElement} from "../../../../../../models/structure-list-element";
import {DepartmentService} from "../../department/department.service";

@Component({
  selector: 'app-office-add',
  templateUrl: './office-add.component.html',
  styleUrls: ['./office-add.component.scss']
})
export class OfficeAddComponent implements OnInit {

    @Input() structureAddElement: StructureAddElement = new StructureAddElement;

    departments: StructureListElement[];

    constructor(private departmentService:DepartmentService,private officeService:OfficeService,private router:Router) {
    }

    ngOnInit() {
        // this.filterUsers(null);
        this.getDepartments();
    }

    getDepartments(){
        this.departmentService.getAll().subscribe(departmentListElement=> {this.departments=departmentListElement});
    }

    companyAdd(){
        this.officeService.createOffice(this.structureAddElement);
        this.router.navigate(['/admin/offices']);
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
