package com.ot.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import com.ot.model.Staff;
import com.ot.repo.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class StaffController {
	@Autowired
	private StaffRepo repo;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("staff", new Staff());
		return "register";
	}

	@PostMapping("/process")
	public String process(Staff staff, @RequestParam("fileImage") MultipartFile multipartFile) throws IOException {

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		staff.setSignature(fileName);

		Staff savedStaff = repo.save(staff);

		String uploadDir = "./signature/" + savedStaff.getId();
		

		Path uploadPath = Paths.get(uploadDir);

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			System.out.println(filePath.toFile().getAbsolutePath());
			
			Files.copy(inputStream, filePath,StandardCopyOption.REPLACE_EXISTING);

		} catch (IOException e) {
			throw new IOException("Could not save uploaded file : " + fileName);
		}

		return "redirect:/";
	}
	
	@GetMapping("/show")
	public String show(Model model) {
		model.addAttribute("staffs", repo.findAll());
		return "show";
	}
	

}
