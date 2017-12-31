package com.maishealth.maishealth.usuario.persistencia;

public class ConstantePopularBanco {
    public static final String INSERIR_USUARIO = "INSERT INTO `usuario` (`email`,`senha`) VALUES " +
            "('a@gmail.com','111111')," +
            "('b@gmail.com','111111')," +
            "('c@gmail.com','111111')," +
            "('d@gmail.com','111111')," +
            "('e@gmail.com','111111')," +
            "('f@gmail.com','111111')," +
            "('g@gmail.com','111111')," +
            "('h@gmail.com','111111')," +
            "('i@gmail.com','111111')," +
            "('j@gmail.com','111111');";

    public static final String INSERIR_PESSOA = "INSERT INTO `pessoa` (`nome`,`sexo`,`data_nasc`,`cpf`,`id_est_usuario`) VALUES " +
            "('Maite Stokes','Feminino','19500212','079.476.434-00',1)," +
            "('Kane Kramer','Masculino','19870515','079.476.434-01',2)," +
            "('Jenette Caldwell','Feminino','19881201','079.476.434-02',3)," +
            "('Nomlanga Dennis','Masculino','19900212','079.476.434-03',4)," +
            "('Leigh Day','Masculino','20001120','079.476.434-04',5)," +
            "('Laith Miller','Feminino','19850417','079.476.434-05',6)," +
            "('Amela Anderson','Feminino','19400123','079.476.434-06',7)," +
            "('Lucy Whitehead','Feminino','19730202','079.476.434-07',8)," +
            "('Indigo Ortega','Masculino','19870227','079.476.434-08',9)," +
            "('Akeem Stout','Feminino','19950505','079.476.434-09',10);";

    public static final String INSERIR_PACIENTE = "INSERT INTO `paciente` (`tipo_sangue`,`id_est_usuario`) VALUES " +
            "('A-',1)," +
            "('B+',2)," +
            "('O+',3)," +
            "('B+',4)," +
            "('A+',5)," +
            "('B-',6)," +
            "('A+',7)," +
            "('AB+',8)," +
            "('A-',9)," +
            "('O-',10);";

    public static final String INSERIR_MEDICO = "INSERT INTO `medico` (`crm`,`id_est_usuario`,`estado`,`especialidade`) VALUES " +
            "('111.100.',1,'PE','Clínico Geral')," +
            "('111.101.',2,'AC','Pediatria')," +
            "('111.102.',3,'RJ','Clínico Geral')," +
            "('111.103.',4,'SP','Cadiologia')," +
            "('111.104.',5,'BA','Cadiologia');";

    public static final String INSERIR_MEDICAMENTO = "INSERT INTO 'medicamento' ('nome') VALUES" +
            "('rivotril')," +
            "('Neosaldina')," +
            "('Dorflex')," +
            "('Diazepan')," +
            "('Lasartana');";

}
