import { Component, OnInit } from '@angular/core';
import {DepartmentService} from "../department.service";
import {TranslateService} from "@ngx-translate/core";
import {StructureListElement} from "../../../../../../models/structure-elements";


@Component({
  selector: 'app-department-list',
  templateUrl: './department-list.component.html',
  styleUrls: ['./department-list.component.scss']
})
export class DepartmentListComponent implements OnInit {

    public deletedFilter = false;
   departments: StructureListElement[];

    constructor(private departmentService : DepartmentService, private translate:TranslateService)
    {
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

    getAddress(department:StructureListElement): string {
        if (department.flatNumber== null || department.flatNumber === "0") {
            return (department.street + ' ' + department.buildingNumber);
        } else {
            return (department.street + ' ' + department.buildingNumber + ' / ' + department.flatNumber);
        }
    }

    filterUsers(searchText: string) {
        this.departmentService.getAll().subscribe(departments => {
            if (!departments) {
                this.departments = [];
                return;
            }
            if (!searchText || searchText.length < 2) {
                if (this.deletedFilter) {
                    this.departments = departments.filter(it => {
                        return it.deleted === !this.deletedFilter;
                    });
                } else {
                    this.departments = departments;
                }
                return;
            }

            searchText = searchText.toLowerCase();
            this.departments = departments.filter(it => {
                const range = it.name+' '+it.companyName+' '+it.departmentName+' '+it.description+' '+it.city+' '+it.street+' '+it.zipCode;
                const ok = range.toLowerCase().includes(searchText);
                if (!this.deletedFilter) {
                    return ok;
                } else {
                    return ok && it.deleted === !this.deletedFilter;
                }
            });
        });
    }

    delete(structure: StructureListElement) {
        this.departmentService.deleteDepartament(String(structure.id)).subscribe(resp => {
            this.getDepartments()
        });
    }

}
