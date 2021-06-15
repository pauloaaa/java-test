package br.com.wa.http.domain.request;

import br.com.wa.domain.user.User;
import java.util.List;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersRequest {
	
	@Size(min = 2, max = 50)
	private List<User> users;
	
}