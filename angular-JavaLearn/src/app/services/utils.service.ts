import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UtilsService {

  constructor(private router: Router) { }

  // handling tab button in text areas as "\t"
  handleKeydown(event:any) {
    if (event.key == 'Tab') {
        event.preventDefault();
        var start = event.target.selectionStart;
        var end = event.target.selectionEnd;
        event.target.value = event.target.value.substring(0, start) + '\t' + event.target.value.substring(end);
        event.target.selectionStart = event.target.selectionEnd = start + 1;
    }
  }

  reloadComponent(path: String, obj?: any){
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([path], {state: obj});
    }); 
  }
}
