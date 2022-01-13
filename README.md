<b>USER</b>: Ainda não criada nem iniciada.<br>
https://github.com/FabioNazario/monstro-api <br>
https://github.com/FabioNazario/seguranca <br>
https://github.com/FabioNazario/eurekaServer <br>
https://github.com/FabioNazario/configServer <br>

Obs: Todas as aplicações então incompletas e sujeitas a modificações

localhost:8000/auth

body:
```json
{
	"user": "fabio",
	"pass": "123456"
}
```
retorno:

```json
{
	"type": "Bearer",
	"token": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJWQVJJQVZFTF9BUVVJPyIsInN1YiI6IjEiLCJpYXQiOjE2NDIwNTUwODIsImV4cCI6MTY0MjA3NjY4MiwidXNlciI6ImZhYmlvIn0.IjeIFqfslyM5455FBvRofYoU9DJCve4MIsJTotSUjlw"
}
```
