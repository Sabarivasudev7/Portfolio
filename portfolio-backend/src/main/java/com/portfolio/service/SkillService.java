package com.portfolio.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.portfolio.entity.Skill;
import com.portfolio.repository.SkillRepository;

@Service
public class SkillService {
    private final SkillRepository repository;

    public SkillService(SkillRepository repository) {
        this.repository = repository;
    }

    public List<Skill> getAllSkills() {
        return repository.findAll();
    }

    public Skill saveSkill(Skill skill) {
        return repository.save(skill);
    }

    public Skill updateSkill(Long id, Skill updatedSkill) {
        return repository.findById(id)
                .map(skill -> {
                    skill.setName(updatedSkill.getName());
                    skill.setCategory(updatedSkill.getCategory());
                    skill.setPercentage(updatedSkill.getPercentage());
                    skill.setIconName(updatedSkill.getIconName());
                    return repository.save(skill);
                })
                .orElse(null);
    }


    public boolean deleteSkill(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
