import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FuncionarioService } from 'src/app/services/funcionario.service';

@Component({
  selector: 'app-funcionario-form',
  templateUrl: './funcionario-form.component.html',
  styleUrls: ['./funcionario-form.component.css']
})
export class FuncionarioFormComponent {
  formGroup: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private funcionarioService: FuncionarioService,
              private router: Router) {
    this.formGroup = formBuilder.group({
      nome:['', Validators.required],
      funcao:['', Validators.required],
      idade:['', Validators.required]
    })
  }

  onSubmit() {
    if (this.formGroup.valid) {
      const novoFuncionario = this.formGroup.value;
      this.funcionarioService.salvar(novoFuncionario).subscribe({
        next: (funcionarioCadastrado) => {
          this.router.navigateByUrl('/funcionarios/list');
        },
        error: (err) => {
          console.log('Erro ao salvar' + JSON.stringify(err));
        }
      })
    
    }
  }

}
