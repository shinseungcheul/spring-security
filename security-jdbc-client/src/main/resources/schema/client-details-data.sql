insert into oauth_client_details(client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, 
						authorities, access_token_validity, refresh_token_validity, 
						additional_information, autoapprove) 
values ('client-first', 'resource-api', '1234', 'read,write,turst', 'password,authorization_code,refresh_token,implicit', null,
		null, 3600, 6000, null, false);

insert into oauth_client_details(client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, 
						authorities, access_token_validity, refresh_token_validity, 
						additional_information, autoapprove) 
values ('trust-client-first', 'resource-api', '1234', 'read,write,turst', 'password,authorization_code,refresh_token,implicit', null,
		null, 3600, 6000, null, false);

		
