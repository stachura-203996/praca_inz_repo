import {Component, Input, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {OfficeService} from "../office.service";
import {DepartmentService} from "../../department/department.service";
import {StructureAddElement, StructureListElement} from "../../../../../../models/structure-elements";
import {TranslateService} from "@ngx-translate/core";
import {CompanyService} from "../../../administration/company/company.service";

@Component({
    selector: 'app-office-add',
    templateUrl: './office-add.component.html',
    styleUrls: ['./office-add.component.scss']
})
export class OfficeAddComponent implements OnInit {

    @Input() structureAddElement: StructureAddElement = new StructureAddElement;

    departments: StructureListElement[];

    constructor(private officeService: OfficeService, private departmentService: DepartmentService, private translate: TranslateService, private router: Router) {
        this.translate.addLangs(['en', 'pl']);
        this.translate.setDefaultLang('pl');
        const browserLang = this.translate.getBrowserLang();
        this.translate.use(browserLang.match(/en|pl/) ? browserLang : 'pl');
    }

    ngOnInit() {
        this.getDepartments();
    }

    getDepartments() {
        this.departmentService.getAll().subscribe(companyListElement => {
            this.departments = companyListElement
        });
    }

    officeAdd() {
        this.officeService.createOffice(this.structureAddElement).subscribe(resp => {
            this.router.navigateByUrl('/admin/offices');
        });
    }

    clear() {
        this.structureAddElement = new StructureAddElement();
    }

}
