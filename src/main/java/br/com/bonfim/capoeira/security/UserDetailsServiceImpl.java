package br.com.bonfim.capoeira.security;

import br.com.bonfim.capoeira.Repository.AlunoRepository;
import br.com.bonfim.capoeira.model.cadastro.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Aluno> oAluno = alunoRepository.findByEmail(email);
        if (!oAluno.isPresent()){
            throw new UsernameNotFoundException(email);
        }
        Aluno aluno = oAluno.get();
        return new UserDetailsSecurity(aluno.getId(), aluno.getEmail(), aluno.getSenha(), aluno.getPerfis());
    }
}
