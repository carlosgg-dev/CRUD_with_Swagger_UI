package org.example.element;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@Builder
@Schema(name = "ElementApi", title = "Element API", description = "Element details")
public class ElementApi {

	@Schema(description = "Element ID", example = "123")
	@NotNull
	int id;

	@Schema(description = "Element name", example = "Name")
	@NotBlank
	String name;
}
