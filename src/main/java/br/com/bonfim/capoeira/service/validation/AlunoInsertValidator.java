package br.com.bonfim.capoeira.service.validation;

import br.com.bonfim.capoeira.Repository.AlunoRepository;
import br.com.bonfim.capoeira.Util.CPFCNPJ;
import br.com.bonfim.capoeira.exception.FieldMessage;
import br.com.bonfim.capoeira.model.DTO.AlunoNewDTO;
import br.com.bonfim.capoeira.model.cadastro.Aluno;
import br.com.bonfim.capoeira.model.cadastro.tipoEnum.TipoCliente;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlunoInsertValidator implements ConstraintValidator<AlunoInsert, AlunoNewDTO> {

    @Autowired
    private AlunoRepository repo;

    @Override
    public void initialize(AlunoInsert ann) {
    }

    @Override
    public boolean isValid(AlunoNewDTO objDto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !CPFCNPJ.isValidCPF(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }

        if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !CPFCNPJ.isValidCNPJ(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
        }

        Optional<Aluno> aux = repo.findByEmail(objDto.getEmail());
        if (aux.isPresent()) {
            list.add(new FieldMessage("email", "Email já existente"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
