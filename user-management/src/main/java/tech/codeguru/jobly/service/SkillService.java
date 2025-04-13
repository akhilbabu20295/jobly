package tech.codeguru.jobly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.codeguru.jobly.entity.Skills;
import tech.codeguru.jobly.entity.dto.request.SkillDTO;
import tech.codeguru.jobly.repository.SkillRepository;

import java.util.List;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public Skills createSkill(SkillDTO request) {
        if (skillRepository.findByName(request.getName()).isPresent()) {
            throw new RuntimeException("Skill already exists");
        }
        Skills skill = new Skills();
        skill.setName(request.getName());
        skill.setDescription(request.getDescription());
        return skillRepository.save(skill);
    }

    public List<Skills> getAllSkills() {
        return skillRepository.findAll();
    }

    public Skills updateSkill(Long id, SkillDTO request) {
        Skills existing = skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));
        existing.setName(request.getName());
        existing.setDescription(request.getDescription());
        return skillRepository.save(existing);
    }

    public void deleteSkill(Long id) {
        if (!skillRepository.existsById(id)) {
            throw new RuntimeException("Skill not found");
        }
        skillRepository.deleteById(id);
    }
}
