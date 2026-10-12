package com.portfolio.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.portfolio.entity.Skill;
import com.portfolio.service.SkillService;

@RestController
@RequestMapping("/api/skills")
public class SkillController {
    private final SkillService service;

    public SkillController(SkillService service) {
        this.service = service;
    }

    @GetMapping
    public List<Skill> getSkills() {
        return service.getAllSkills();
    }

    @PostMapping
    public Skill addSkill(@RequestBody Skill skill) {
        return service.saveSkill(skill);
    }

    @PutMapping("/{id}")
    public Skill updateSkill(@PathVariable Long id, @RequestBody Skill skill) {
        return service.updateSkill(id, skill);
    }

    @DeleteMapping("/{id}")
    public String deleteSkill(@PathVariable Long id) {
        boolean deleted = service.deleteSkill(id);
        return deleted ? "Skill deleted successfully" : "Skill not found";
    }
}
