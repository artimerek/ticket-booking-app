Instrukcja:

Logowanie jako admin:
http://localhost:8070/ lub klinięcie w odpowiednie hiperłącze.

Username: konrad@admin.com
Password : test

Rola administratora umożliwia generowanie losowych eventów 
oraz tworzenie ich przez formularz.
Admin może też tworzyć eventy.

By się zarejestrować trzeba udać się pod adres:
http://localhost:8070/register lub klinięcie w odpowiednie hiperłącze.
Otrzymamy wtedy rolę zwykłego użytkownika który może tylko
przeglądać swoje bilety oraz je bookować.

By zalogować się do bazy danych
http://localhost:8070/h2-console lub bedac zalogowanym jako admin 
zakładka database/ 
JDBC URL:	jdbc:h2:mem:mydb
User Name: 	sa
Password: (nie trzeba nic wpisywać)