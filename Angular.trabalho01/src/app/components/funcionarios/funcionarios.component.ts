import { Component, OnInit } from '@angular/core';
import { Funcionario } from 'src/app/models/funcionario.model';
import { FuncionarioService } from 'src/app/services/funcionario.service';

@Component({
  selector: 'app-funcionarios',
  templateUrl: './funcionarios.component.html',
  styleUrls: ['./funcionarios.component.css']
})
export class FuncionariosComponent implements OnInit {

  funcionarios: Funcionario[] = [];

  constructor(private funcionarioService: FuncionarioService) {}

  ngOnInit(): void {
    this.funcionarioService.getFuncionarios().subscribe(data => {
      this.funcionarios = data;
    });
  }

}