package com.elyashevich.ecommerce.util;

import lombok.experimental.UtilityClass;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@UtilityClass
public class ValidationHandlerUtil {

    public static void handleValidation(final Model model, final BindingResult bindingResult) {
        model.addAttribute("errors", bindingResult.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList()
        );
    }
}
