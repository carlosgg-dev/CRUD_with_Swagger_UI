package org.example.element;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@Schema(name = "ElementApi", title = "Element API", description = "Element details")
public class ElementApi {

	@Schema(description = "Element ID", example = "123")
	@Positive
	int id;

	@Schema(description = "Element name", example = "Name")
	@NotBlank
	String name;

	@Schema(description = "Element type", example = "AC")
	@NotBlank
	String type;
}
