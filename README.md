# learning-lab

Coursework from my DAM studies (2023–2025), cleaned up and sorted by language.
Small exercises, not products: a loop here, a sorting routine there, a few
console games that got a bit out of hand. I keep them together because together
they show the ground I covered.

Most files run on their own. Where something needs a build step or a helper file
it is noted below. Anything that only compiled inside its original IDE project,
or duplicated another exercise, was dropped.

## What's here

### java/

| Folder | What it practices |
|--------|-------------------|
| `connect-four/` | The biggest one. Connect Four with a minimax AI, drawn with Processing, built with Maven. Board model, win detection, score persistence to a file. |
| `sudoku/` | Backtracking solver and a playable grid on the console. Recursion, 2D arrays, input validation. |
| `memory-game/` | A memory/concentration game in two stages (`Fase1`, `Fase2`) — arrays, shuffling with `Collections`, turn loop. |
| `fibonacci/` | Fibonacci as a string builder. Iteration, `StringBuilder`, off-by-one care. |
| `oop-basics/` | First object-oriented exercises: a class with a documented method, a `main` that drives it. |

### python/

| Folder | What it practices |
|--------|-------------------|
| `basics/` | `sys.argv`, `subprocess` (wrapping `ping` and `nslookup`), reading and writing files, `signal` handlers. `llibreria.py` is a small shared module with a PID-file guard and Ctrl-C / SIGTERM handlers that the later exercises import. |
| `control-flow/` | Conditionals and loops from the ground up: a number-guessing game with limited tries, type coercion between strings and numbers, nested-loop exercises (`PythonFirstWalks` … `ThirdWalks`). |

### javascript/

| Folder | What it practices |
|--------|-------------------|
| `exercises/` | 14 short scripts run with `prompt` / `console.log`: capitalising a name, doubling a number, a leap-year function, string length, simple conditionals and functions. The spec they were written against is in the course PDF (not included). |

### css/

Ten layout exercises, one folder each. The target screenshots the instructor
handed out are removed — only my HTML and CSS is here.

| Folder | What it practices |
|--------|-------------------|
| `box-model/` | Margin, padding, border, `box-sizing`. |
| `display/` | `block` vs `inline` vs `inline-block`, three variants. |
| `relative-units/` | `%`, `em`, `rem`, `vh` / `vw`. |
| `position/` | `static`, `relative`, `absolute`, `fixed`. |
| `flag/` | Draw a flag with pure CSS — no images. |
| `float/` | Text wrapping around floated elements, clearing. |
| `media-query/` | Breakpoints and a responsive layout. |
| `flexbox/` | `flex-direction`, `justify-content`, `align-items`, comparing flex against float and tables. |
| `cascade/` | Specificity and inheritance. |
| `postal-card/` | A postcard layout putting the box model and positioning together. |

### bash/

An MQTT chat built out of shell scripts: `chat_client.sh` wraps
`mosquitto_pub` / `mosquitto_sub`, `encrypt_decrypt.sh` pipes messages through
`openssl enc`, `chat_app.sh` ties them together. Points at a local broker
(`127.0.0.1`) — run your own Mosquitto.

### databases/

Theory notes from the databases module (in Spanish / Catalan): data vs
information, data models, the three levels of schema independence, types of
database user. Plain notes, kept as Markdown.

## Running things

- **Java single files:** `javac File.java && java File` (JDK 17+). `connect-four`
  needs Maven and pulls Processing 4 and JOGL from Maven Central: `cd
  java/connect-four && ./mvnw compile`.
- **Python:** `python file.py`. The `basics/` scripts that import `llibreria`
  expect it in the same folder, which it is.
- **JavaScript:** these use browser `prompt`, so run them in the browser console
  or with `node` after swapping `prompt` for stdin.
- **CSS:** open the `index.html` in a browser.
- **Bash:** needs `mosquitto-clients` and `openssl`, plus a broker on
  `localhost:1883`.

## License

PolyForm Noncommercial 1.0.0 — see [LICENSE](LICENSE). Personal, non-commercial
use only.
