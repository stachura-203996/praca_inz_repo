import {Component, Input, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {OfficeService} from "../office.service";
import {DepartmentService} from "../../department/department.service";
import {StructureAddElement, StructureListElement} from "../../../../../../models/structure-elements";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-office-add',
  templateUrl: './office-add.component.html',
  styleUrls: ['./office-add.component.scss']
})
export class OfficeAddComponent implements OnInit {

    @Input() structureAddElement: StructureAddElement = new StructureAddElement;

    departments: StructureListElement[];

    constructor(private departmentService:DepartmentService,private officeService:OfficeService,private router:Router,private translate:TranslateService) {
        this.translate.addLangs(['en','pl']);
        this.translate.setDefaultLang('pl');
        const browserLang = this.translate.getBrowserLang();
        this.translate.use(browserLang.match(/en|pl/) ? browserLang : 'pl');
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
