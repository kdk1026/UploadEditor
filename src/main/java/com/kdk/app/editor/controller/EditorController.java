package com.kdk.app.editor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * <pre>
 * -----------------------------------
 * 개정이력
 * -----------------------------------
 * 2024. 6. 9. 김대광	최초작성
 * </pre>
 *
 *
 * @author 김대광
 */
@Controller
@RequestMapping("/editor")
public class EditorController {

	@GetMapping("/tui")
	public ModelAndView tui() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("/editor/tui");
		return mav;
	}

	@GetMapping("/ck")
	public ModelAndView ck() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("/editor/ck");
		return mav;
	}

	@GetMapping("/summer")
	public ModelAndView summer() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("/editor/summer");
		return mav;
	}

	@GetMapping("/quill")
	public ModelAndView quill() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("/editor/quill");
		return mav;
	}

}
