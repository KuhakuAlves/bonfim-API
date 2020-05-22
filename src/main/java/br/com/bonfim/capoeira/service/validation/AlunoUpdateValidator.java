package br.com.bonfim.capoeira.service.validation;

import br.com.bonfim.capoeira.Repository.AlunoRepository;
import br.com.bonfim.capoeira.exception.FieldMessage;
import br.com.bonfim.capoeira.model.DTO.AlunoDTO;
import br.com.bonfim.capoeira.model.cadastro.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AlunoUpdateValidator implements ConstraintValidator<AlunoUpdate, AlunoDTO> {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AlunoRepository repo;

    @Override
    public void initialize(AlunoUpdate ann) {
    }

    @Override
    public boolean isValid(AlunoDTO objDto, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Long uriId = Long.parseLong(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Optional<Aluno> aux = repo.findByEmail(objDto.getEmail());
        Aluno alu = aux.get();
        if (aux.isPresent() && !alu.getId().equals(uriId)) {
            list.add(new FieldMessage("email", "Email já existente, Aluno: "+ alu.getNome()));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
