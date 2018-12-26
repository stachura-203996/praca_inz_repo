import {Component, Input, OnInit} from '@angular/core';
import {StructureAddElement} from "../../../../../../models/structure-add-element";
import {StructureListElement} from "../../../../../../models/structure-list-element";
import {CompanyService} from "../../../administration/company/company.service";
import {DepartmentService} from "../department.service";
import {ActivatedRoute, Router} from "@angular/router";
import {StructureEditElement} from "../../../../../../models/structure-edit-element";

@Component({
    selector: 'app-department-edit',
    templateUrl: './department-edit.component.html',
    styleUrls: ['./department-edit.component.scss']
})
export class DepartmentEditComponent implements OnInit {

    @Input() structureEditElement: StructureEditElement = new StructureAddElement;

    companies: StructureListElement[];

    constructor(private route: ActivatedRoute,private companyService: CompanyService, private departmentService: DepartmentService, private router: Router) {
    }

    ngOnInit() {
        this.getCompanies();
    }

    getDepartment() {
        const id = this.route.snapshot.paramMap.get('id');
        this.departmentService.getDepartmentEdit(id).subscribe(x=>this.structureEditElement=x);
    }

    getCompanies() {
        this.companyService.getAll().subscribe(companyListElement => {
            this.companies = companyListElement
        });
    }

    departmentAdd() {
        this.departmentService.createDepartment(this.structureEditElement).subscribe(resp => {
            this.router.navigateByUrl('/admin/departments');
        });
    }

    clear() {
        this.structureEditElement.description = null;
        this.structureEditElement.zipCode = null;
        this.structureEditElement.buildingNumber = null;
        this.structureEditElement.city = null;
        this.structureEditElement.flatNumber = null;
        this.structureEditElement.street = null;
        this.structureEditElement.name = null;
    }

}
