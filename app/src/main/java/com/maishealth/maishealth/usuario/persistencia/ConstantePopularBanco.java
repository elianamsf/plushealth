package com.maishealth.maishealth.usuario.persistencia;

public class ConstantePopularBanco {
    public static final String INSERIR_USUARIO = " INSERT INTO `usuario` (`email`,`senha`) VALUES ('a@gmail.com','111111'),('b@gmail.com','111111'),('c@gmail.com','111111'),('d@gmail.com','111111'),('e@gmail.com','111111'),('f@gmail.com','111111'),('g@gmail.com','111111'),('h@gmail.com','111111'),('i@gmail.com','111111'),('j@gmail.com','111111');";

    public static final String INSERIR_PESSOA = "INSERT INTO `pessoa` (`nome`,`sexo`,`data_nasc`,`cpf`,`id_est_usuario`) VALUES ('Maite Stokes','Feminino','19871212','079.476.434-00',1),('Kane Kramer','Masculino','19871212','079.476.434-01',2),('Jenette Caldwell','Masculino','19871212','079.476.434-02',3),('Nomlanga Dennis','Masculino','19871212','079.476.434-03',4),('Leigh Day','Masculino','19871212','079.476.434-04',5),('Laith Miller','Feminino','19871212','079.476.434-05',6),('Amela Anderson','Feminino','19871212','079.476.434-06',7),('Lucy Whitehead','Feminino','19871212','079.476.434-07',8),('Indigo Ortega','Masculino','19871212','079.476.434-08',9),('Akeem Stout','Feminino','19871212','079.476.434-09',10);";

    public static final String INSERIR_PACIENTE = "INSERT INTO `paciente` (`tipo_sangue`,`id_est_usuario`) VALUES ('A+',1),('A+',2),('A+',3),('A+',4),('A+',5),('A+',6),('A+',7),('A+',8),('A+',9),('A+',10);";

    public static final String INSERIR_MEDICO = "INSERT INTO `medico` (`crm`,`id_est_usuario`,`estado`,`especialidade`) VALUES ('111.100.',1,'PE','Clinico Geral'),('111.101.',2,'PE','Pediatria'),('111.102.',3,'PE','Clinico Geral'),('111.103.',4,'PE','Cadiologia'),('111.104.',5,'PE','Cadiologia');";

}
