package com.spring.calculator.service;

import com.spring.calculator.model.Role;
import com.spring.calculator.model.User;
import com.spring.calculator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

// UserDetailsService skirtas gauti su vartotoju susijusią informaciją
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Override
    /*  @Transactional anotacija skirta visoms neatsiejamoms operacijoms valdyti.
        Metodai, kurių veiksmai vykdomi transakcijose pažymimi @Transactional
        Transakcija yra būtina visiems metodams dirbantiems su duomenų baze
        @Transactional pradės transakciją tik jei komponentas sukurtas per Spring ir kviečiamas iš kitos klasės
     */
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        // Peržiūrimos esamos vartotojų rolės (saugomos db 'role' lentelėje)
        // ir priskiriamos esamam vartotojui (saugoma 'user_roles' lentelėje)
        // Tarkime, jeigu 'role' lentelėje yra dvi rolės (user, admin), tai esamas vartotojas jas abi ir gaus
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
