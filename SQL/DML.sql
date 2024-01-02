INSERT INTO public.organization
(org_id, creation_ts, org_desc, org_name, org_owner_user_id, updation_ts)
VALUES(1, now(), 'First Org DESC', 'First Org', 1, now());


INSERT INTO public.user_model
(user_id, creation_ts, email, login_id, "role", updation_ts, org_id)
VALUES(1, now(), 'test@test.com', 'testLogin', 'Admin', now(), 1);

INSERT INTO public.plugin
(plugin_id,org_id, plugin_desc, plugin_name, plugin_type, creation_ts, updation_ts)
VALUES(1,1, 'QlsConnect Desc', 'QlsConnect', 'API_CONNECT', now(), now());

INSERT INTO public.plugin
(plugin_id,org_id, plugin_desc, plugin_name, plugin_type, creation_ts, updation_ts)
VALUES(4,1, 'File Desc', 'FileConnect', 'File_CONNECT', now(), now());


INSERT INTO public.master_data
(ref_id, creation_ts, ref_desc, ref_has_schema, ref_name, ref_schema, ref_type, ref_value, ref_version, updation_ts)
VALUES(1, '2023-08-04 18:18:45.611', 'entity desc', true, 'NetLocation', '{"ID": "urn:com.ibm.pathfinder.model.dataobs:NetLocation:1.1.0", "type": "object", "allOf": [{"type": "object", "required": ["json_schema_ref", "edf_id"], "properties": {"edf_id": {"type": "string", "format": "uuid", "description": "Reference to the entity."}, "json_schema_ref": {"type": "string", "pattern": "^urn:(?<groupid>[a-z._-]+):(?<schemaid>[a-z._-]+):(?<version>[0-9]+\\.[0-9]+\\.[0-9]+)$", "description": "The id of the JSON schema used to encode the payload."}}}], "$schema": "https://json-schema.org/draft/2020-12/schema", "required": ["host"], "properties": {"host": {"type": "string", "description": "host IP"}, "port": {"type": "integer", "nullable": true, "description": "port number, optional"}}, "description": "A network location, can also specify a service endpoint.", "unevaluatedProperties": true}'::jsonb, 'entity', '1', '1.0', '2023-08-04 18:18:45.611');
INSERT INTO public.master_data
(ref_id, creation_ts, ref_desc, ref_has_schema, ref_name, ref_schema, ref_type, ref_value, ref_version, updation_ts)
VALUES(2, '2023-08-04 18:18:45.772', 'entity desc', true, 'Certificate', '{"ID": "urn:com.ibm.pathfinder.model.crypto:Certificate:1.0.0", "type": "object", "allOf": [{"type": "object", "required": ["json_schema_ref", "edf_id"], "properties": {"edf_id": {"type": "string", "format": "uuid", "description": "Reference to the entity."}, "json_schema_ref": {"type": "string", "pattern": "^urn:(?<groupid>[a-z._-]+):(?<schemaid>[a-z._-]+):(?<version>[0-9]+\\.[0-9]+\\.[0-9]+)$", "description": "The id of the JSON schema used to encode the payload."}}}], "$schema": "https://json-schema.org/draft/2020-12/schema", "required": ["issuer", "signature_algo"], "properties": {"format": {"type": "string", "nullable": true, "description": "The format (representation?) of the certificate."}, "issuer": {"type": "string", "description": "The issuer of the certificate."}, "subject": {"type": "string", "nullable": true, "description": "The subject for which the certificate was issued."}, "version": {"type": "string", "nullable": true, "description": "The version of the certificate."}, "algorithm": {"type": "string", "nullable": true, "description": "The algorithm used to verify the certificate."}, "not_after": {"type": "string", "format": "date", "nullable": true, "description": "The end of the validity period."}, "extensions": {"type": "string", "nullable": true, "description": "Extensions."}, "not_before": {"type": "string", "format": "date", "nullable": true, "description": "The start of the validity period."}, "signature_algo": {"type": "string", "description": "The the algorithm that was used to sign the certificate."}, "signature_algo_oid": {"type": "string", "nullable": true, "description": "The object identifier of the signature algorithm."}}, "description": "A cryptographic certificate.", "unevaluatedProperties": true}'::jsonb, 'entity', '2', '1.0', '2023-08-04 18:18:45.772');
INSERT INTO public.master_data
(ref_id, creation_ts, ref_desc, ref_has_schema, ref_name, ref_schema, ref_type, ref_value, ref_version, updation_ts)
VALUES(3, '2023-08-04 18:18:45.930', 'entity desc', true, 'Ciphersuite', '{"ID": "urn:com.ibm.pathfinder.model.crypto:Ciphersuite:1.0.0", "type": "object", "allOf": [{"type": "object", "required": ["json_schema_ref", "edf_id"], "properties": {"edf_id": {"type": "string", "format": "uuid", "description": "Reference to the entity."}, "json_schema_ref": {"type": "string", "pattern": "^urn:(?<groupid>[a-z._-]+):(?<schemaid>[a-z._-]+):(?<version>[0-9]+\\.[0-9]+\\.[0-9]+)$", "description": "The id of the JSON schema used to encode the payload."}}}], "$schema": "https://json-schema.org/draft/2020-12/schema", "required": ["name"], "properties": {"name": {"type": "string", "description": "The name of the ciphersuite indicating a set of algorithms such as, ''TLS_AES_128_GCM_SHA256''."}, "security": {"type": "string", "nullable": true, "description": "A description of the security level provided by this suite."}}, "description": "A grouping of cryptographic entities that are needed to secure a connection.", "unevaluatedProperties": true}'::jsonb, 'entity', '3', '1.0', '2023-08-04 18:18:45.930');
INSERT INTO public.master_data
(ref_id, creation_ts, ref_desc, ref_has_schema, ref_name, ref_schema, ref_type, ref_value, ref_version, updation_ts)
VALUES(4, '2023-08-04 18:18:46.088', 'entity desc', true, 'Cryptographicprotocol', '{"ID": "urn:com.ibm.pathfinder.model.crypto:Cryptographicprotocol:1.0.0", "type": "object", "allOf": [{"type": "object", "required": ["json_schema_ref", "edf_id"], "properties": {"edf_id": {"type": "string", "format": "uuid", "description": "Reference to the entity."}, "json_schema_ref": {"type": "string", "pattern": "^urn:(?<groupid>[a-z._-]+):(?<schemaid>[a-z._-]+):(?<version>[0-9]+\\.[0-9]+\\.[0-9]+)$", "description": "The id of the JSON schema used to encode the payload."}}}], "$schema": "https://json-schema.org/draft/2020-12/schema", "required": ["name", "type"], "properties": {"name": {"type": "string", "description": "The name of the protocol such as ''TLS'' or ''SSH''."}, "type": {"type": "string", "description": "The type of the protocol, e.g. IPSEC."}, "purpose": {"type": "string", "nullable": true, "description": "The purpose of the protocol."}, "version": {"type": "string", "nullable": true, "description": "The protocol version such as ''1.3''"}}, "description": "A cryptographic protocol.", "unevaluatedProperties": true}'::jsonb, 'entity', '4', '1.0', '2023-08-04 18:18:46.088');
INSERT INTO public.master_data
(ref_id, creation_ts, ref_desc, ref_has_schema, ref_name, ref_schema, ref_type, ref_value, ref_version, updation_ts)
VALUES(5, '2023-08-04 18:18:46.251', 'entity desc', true, 'Hash', '{"ID": "urn:com.ibm.pathfinder.model.crypto:Hash:1.0.0", "type": "object", "allOf": [{"type": "object", "required": ["json_schema_ref", "edf_id"], "properties": {"edf_id": {"type": "string", "format": "uuid", "description": "Reference to the entity."}, "json_schema_ref": {"type": "string", "pattern": "^urn:(?<groupid>[a-z._-]+):(?<schemaid>[a-z._-]+):(?<version>[0-9]+\\.[0-9]+\\.[0-9]+)$", "description": "The id of the JSON schema used to encode the payload."}}}], "$schema": "https://json-schema.org/draft/2020-12/schema", "required": ["algorithm"], "properties": {"algorithm": {"type": "string", "description": "The name of the hash algorithm."}}, "description": "A hash algorithm.", "unevaluatedProperties": true}'::jsonb, 'entity', '5', '1.0', '2023-08-04 18:18:46.251');
INSERT INTO public.master_data
(ref_id, creation_ts, ref_desc, ref_has_schema, ref_name, ref_schema, ref_type, ref_value, ref_version, updation_ts)
VALUES(6, '2023-08-04 18:18:46.409', 'entity desc', true, 'Pkencryption', '{"ID": "urn:com.ibm.pathfinder.model.crypto:Pkencryption:1.0.0", "type": "object", "allOf": [{"type": "object", "required": ["json_schema_ref", "edf_id"], "properties": {"edf_id": {"type": "string", "format": "uuid", "description": "Reference to the entity."}, "json_schema_ref": {"type": "string", "pattern": "^urn:(?<groupid>[a-z._-]+):(?<schemaid>[a-z._-]+):(?<version>[0-9]+\\.[0-9]+\\.[0-9]+)$", "description": "The id of the JSON schema used to encode the payload."}}}], "$schema": "https://json-schema.org/draft/2020-12/schema", "required": ["algorithm", "key_length"], "properties": {"algorithm": {"type": "string", "description": "The name of the public key encryption algorithm."}, "key_length": {"type": "integer", "description": "The length of the encryption key."}}, "description": "A description of a public key encryption.", "unevaluatedProperties": true}'::jsonb, 'entity', '6', '1.0', '2023-08-04 18:18:46.409');
INSERT INTO public.master_data
(ref_id, creation_ts, ref_desc, ref_has_schema, ref_name, ref_schema, ref_type, ref_value, ref_version, updation_ts)
VALUES(7, '2023-08-04 18:18:46.566', 'relation desc', false, 'Has', NULL, 'relation', '7', '1.0', '2023-08-04 18:18:46.566');
INSERT INTO public.master_data
(ref_id, creation_ts, ref_desc, ref_has_schema, ref_name, ref_schema, ref_type, ref_value, ref_version, updation_ts)
VALUES(8, '2023-08-04 18:18:46.724', 'relation desc', false, 'Uses', NULL, 'relation', '8', '1.0', '2023-08-04 18:18:46.724');
INSERT INTO public.master_data
(ref_id, creation_ts, ref_desc, ref_has_schema, ref_name, ref_schema, ref_type, ref_value, ref_version, updation_ts)
VALUES(9, '2023-08-04 18:18:46.884', 'relation desc', false, 'Signs', NULL, 'relation', '9', '1.0', '2023-08-04 18:18:46.884');
INSERT INTO public.master_data
(ref_id, creation_ts, ref_desc, ref_has_schema, ref_name, ref_schema, ref_type, ref_value, ref_version, updation_ts)
VALUES(10, '2023-08-04 18:18:47.043', 'relation desc', false, 'Creates', NULL, 'relation', '10', '1.0', '2023-08-04 18:18:47.043');
INSERT INTO public.master_data
(ref_id, creation_ts, ref_desc, ref_has_schema, ref_name, ref_schema, ref_type, ref_value, ref_version, updation_ts)
VALUES(11, '2023-08-14 19:57:53.599', 'entity desc', true, 'Domain', '{"ID": "urn:com.ibm.pathfinder.model.dataobs:Domain:1.1.0", "type": "object", "allOf": [{"type": "object", "required": ["json_schema_ref", "edf_id"], "properties": {"edf_id": {"type": "string", "format": "uuid", "description": "Reference to the entity."}, "json_schema_ref": {"type": "string", "pattern": "^urn:(?<groupid>[a-z._-]+):(?<schemaid>[a-z._-]+):(?<version>[0-9]+\\.[0-9]+\\.[0-9]+)$", "description": "The id of the JSON schema used to encode the payload."}}}], "$schema": "https://json-schema.org/draft/2020-12/schema", "required": ["dns"], "properties": {"dns": {"type": "string", "description": "The domain distinguishes private network addresses."}}, "description": "A network domain, can also specify a dns endpoint.", "unevaluatedProperties": true}'::jsonb, 'entity', '11', '1.0', '2023-08-14 19:57:53.599');



