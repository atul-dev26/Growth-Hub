import { BrowserRouter, Link, Navigate, Route, Routes } from 'react-router-dom'

const highlights = [
  {
    title: 'Timetable-First Focus',
    text: 'Plan your day in a structured timetable so placement prep stays disciplined and realistic.',
  },
  {
    title: 'Daily Efficiency Engine',
    text: 'Set achievable goals, complete them, and earn a daily score that rewards consistency.',
  },
  {
    title: 'Placement Metrics, Simplified',
    text: 'Striver A2Z, LeetCode, and GitHub progress are summarized clearly - no noise, just the essentials.',
  },
  {
    title: 'Competitive, Not Crowded',
    text: 'Opt-in leaderboards with a small participation fee keep the community focused and serious.',
  },
]

const steps = [
  {
    number: '01',
    title: 'Create Your Timetable',
    text: 'Lay out the sessions you want to complete today in minutes.',
  },
  {
    number: '02',
    title: 'Stay Disciplined',
    text: 'Check off tasks as you finish them and keep your momentum visible.',
  },
  {
    number: '03',
    title: 'Review the Week',
    text: 'See your efficiency score and optional leaderboard rank to stay accountable.',
  },
]

const metrics = [
  { label: 'A2Z Progress', value: '255 / 456' },
  { label: 'LeetCode Solved', value: '318' },
  { label: 'GitHub Streak', value: '21 days' },
]

function Landing() {
  return (
    <div className="page">
      <header className="nav">
        <div className="logo">
          <span className="logo-mark">GH</span>
          <span>GrowthHub</span>
        </div>
        <nav className="nav-links">
          <a href="#product">Product</a>
          <a href="#discipline">Discipline</a>
          <a href="#efficiency">Efficiency</a>
          <a href="#leaderboard">Leaderboards</a>
          <a href="#timetable">Timetable</a>
        </nav>
        <div className="nav-actions">
          <Link className="btn btn-outline" to="/login">
            Sign in
          </Link>
          <Link className="btn btn-solid" to="/login">
            Sign up
          </Link>
        </div>
      </header>

      <main>
        <section className="hero" id="top">
          <div className="hero-copy">
            <p className="eyebrow">Personal productivity + competitive growth</p>
            <h1 className="hero-title">
              A timetable that keeps you disciplined for placements.
            </h1>
            <p className="hero-subtitle">
              GrowthHub is a timetable-first productivity system with daily
              efficiency scoring and clear placement metrics - built for focused
              college students.
            </p>
            <div className="hero-actions">
              <Link className="btn btn-solid" to="/login">
                Start with Google
              </Link>
              <Link className="btn btn-ghost" to="/login">
                Start with GitHub
              </Link>
            </div>
            <div className="hero-metrics">
              {metrics.map((metric) => (
                <div className="metric" key={metric.label}>
                  <p>{metric.label}</p>
                  <span>{metric.value}</span>
                </div>
              ))}
            </div>
          </div>

          <div className="hero-scene" aria-hidden="true">
            <div className="orb orb-1" />
            <div className="orb orb-2" />
            <div className="orb orb-3" />
            <div className="card-stack">
              <div className="card-layer layer-front">
                <div className="card-header">
                  <span className="pill">Today</span>
                  <span className="pill pill-accent">Efficiency 84%</span>
                </div>
                <h3>Daily Focus</h3>
                <div className="card-grid">
                  <div>
                    <p>Striver A2Z</p>
                    <span>255 / 456</span>
                  </div>
                  <div>
                    <p>LeetCode</p>
                    <span>+6 solved</span>
                  </div>
                  <div>
                    <p>GitHub</p>
                    <span>4 commits</span>
                  </div>
                  <div>
                    <p>Streak</p>
                    <span>7 days</span>
                  </div>
                </div>
              </div>
              <div className="card-layer layer-mid">
                <h4>Weekly Consistency</h4>
                <p>5 / 7 days on track</p>
                <div className="bars">
                  <span />
                  <span />
                  <span />
                  <span />
                  <span />
                  <span />
                </div>
              </div>
              <div className="card-layer layer-back">
                <p className="muted">Timetable Block</p>
                <h4>Evening focus window</h4>
                <p>7:30 PM - 9:45 PM</p>
                <p className="muted">Planned study session</p>
              </div>
            </div>
          </div>
        </section>

        <section className="section product" id="product">
          <div className="section-heading">
            <h2>Timetable first. Placement prep stays steady.</h2>
            <p>
              No spreadsheets. No scattered apps. Just a clean, modern system
              that keeps your day structured and your progress visible.
            </p>
          </div>
          <div className="feature-grid">
            {highlights.map((feature) => (
              <article className="feature-card" key={feature.title}>
                <h3>{feature.title}</h3>
                <p>{feature.text}</p>
              </article>
            ))}
          </div>
        </section>

        <section className="section discipline" id="discipline">
          <div className="section-heading">
            <h2>Tech or non-tech, discipline is the advantage</h2>
            <p>
              GrowthHub is built for anyone preparing for placements. Whether you
              are focused on coding or non-technical roles, a strong timetable and
              daily efficiency keep you consistent.
            </p>
          </div>
          <div className="timeline">
            <div className="timeline-item">
              <div className="timeline-node">T</div>
              <article className="timeline-card">
                <h3>Tech Track</h3>
                <p>
                  Structure DSA, LeetCode, and project work into clear daily blocks
                  that build momentum.
                </p>
                <div className="timeline-tags">
                  <span className="badge">DSA</span>
                  <span className="badge">LeetCode</span>
                  <span className="badge">Projects</span>
                </div>
              </article>
            </div>
            <div className="timeline-item">
              <div className="timeline-node">NT</div>
              <article className="timeline-card">
                <h3>Non-Tech Track</h3>
                <p>
                  Plan aptitude, communication, and domain prep with the same
                  discipline-first timetable and efficiency score.
                </p>
                <div className="timeline-tags">
                  <span className="badge">Aptitude</span>
                  <span className="badge">Communication</span>
                  <span className="badge">Domain prep</span>
                </div>
              </article>
            </div>
          </div>
        </section>

        <section className="section efficiency" id="efficiency">
          <div className="split">
            <div>
              <h2>Timetable + efficiency in one flow</h2>
              <p>
                GrowthHub rewards consistency over intensity. Plan your sessions,
                check them off, and keep your efficiency score honest.
              </p>
              <ul className="list">
                <li>Timetable blocks keep your day structured.</li>
                <li>Completion-based scoring keeps goals honest.</li>
                <li>See weekly progress and recovery days clearly.</li>
              </ul>
              <button className="btn btn-solid">Build today&apos;s plan</button>
            </div>
            <div className="panel">
              <div className="panel-header">
                <span>Today&apos;s Plan</span>
                <strong>6 goals</strong>
              </div>
              <div className="panel-body">
                <div className="panel-row">
                  <span className="dot active" />
                  <div>
                    <p>Revise A2Z arrays</p>
                    <small>40 mins</small>
                  </div>
                </div>
                <div className="panel-row">
                  <span className="dot active" />
                  <div>
                    <p>LeetCode medium set</p>
                    <small>3 problems</small>
                  </div>
                </div>
                <div className="panel-row">
                  <span className="dot" />
                  <div>
                    <p>Update GitHub project README</p>
                    <small>20 mins</small>
                  </div>
                </div>
              </div>
              <div className="panel-footer">
                <span>Efficiency</span>
                <strong>84%</strong>
              </div>
            </div>
          </div>
        </section>

        <section className="section leaderboard" id="leaderboard">
          <div className="split reverse">
            <div>
              <h2>Leaderboards that stay meaningful</h2>
              <p>
                Compete with friends who opt in. A small participation fee keeps
                the leaderboard focused and spam-free.
              </p>
              <div className="badge-row">
                <span className="badge">Opt-in only</span>
                <span className="badge">Weekly consistency</span>
                <span className="badge">Total solved</span>
              </div>
            </div>
            <div className="leaderboard-panel">
              <div className="leaderboard-header">
                <h3>Weekly Consistency</h3>
                <span>Top 5</span>
              </div>
              <div className="leaderboard-row">
                <span>1</span>
                <div>
                  <p>Arjun S.</p>
                  <small>92% efficiency</small>
                </div>
                <strong>28 pts</strong>
              </div>
              <div className="leaderboard-row active">
                <span>2</span>
                <div>
                  <p>You</p>
                  <small>88% efficiency</small>
                </div>
                <strong>26 pts</strong>
              </div>
              <div className="leaderboard-row">
                <span>3</span>
                <div>
                  <p>Meera K.</p>
                  <small>85% efficiency</small>
                </div>
                <strong>25 pts</strong>
              </div>
            </div>
          </div>
        </section>

        <section className="section insights" id="timetable">
          <div className="section-heading">
            <h2>Discipline that lasts beyond day one</h2>
            <p>
              A clear timetable and daily efficiency loop keep you focused,
              consistent, and ready for placement season.
            </p>
          </div>
          <div className="steps">
            {steps.map((step) => (
              <div className="step-card" key={step.number}>
                <span>{step.number}</span>
                <h3>{step.title}</h3>
                <p>{step.text}</p>
              </div>
            ))}
          </div>
        </section>

        <section className="cta" id="cta">
          <div>
            <h2>Ready to build your placement momentum?</h2>
            <p>
              Start today with Google or GitHub. GrowthHub keeps you focused,
              consistent, and competitive.
            </p>
          </div>
          <div className="cta-actions">
            <Link className="btn btn-solid" to="/login">
              Sign in with Google
            </Link>
            <Link className="btn btn-outline" to="/login">
              Sign in with GitHub
            </Link>
          </div>
        </section>
      </main>

      <footer className="footer">
        <div>
          <p>GrowthHub</p>
          <small>Timetable and efficiency system for placement prep.</small>
        </div>
        <small>Built for placement-focused students.</small>
      </footer>
    </div>
  )
}

function Login() {
  const resolveApiBase = () => {
    const envBase = import.meta.env.VITE_API_BASE_URL
    if (envBase) {
      return envBase.replace(/\/$/, '')
    }

    if (typeof window !== 'undefined') {
      const { protocol, hostname } = window.location
      if (hostname === 'localhost' || hostname === '127.0.0.1') {
        return `${protocol}//${hostname}:8080/api`
      }
      return `${protocol}//${hostname}/api`
    }

    return 'http://localhost:8080/api'
  }

  const apiBase = resolveApiBase()
  const googleAuthUrl = `${apiBase}/oauth2/authorization/google`
  const githubAuthUrl = `${apiBase}/oauth2/authorization/github`

  return (
    <div className="auth-page">
      <div className="auth-shell">
        <header className="auth-top">
          <div className="logo">
            <span className="logo-mark">GH</span>
            <span>GrowthHub</span>
          </div>
          <Link className="back-link" to="/">
            Back to landing
          </Link>
        </header>

        <div className="auth-card">
          <div className="auth-form">
            <p className="eyebrow">Welcome back</p>
            <h1>Sign in to your timetable</h1>
            <p className="auth-subtitle">
              Keep your discipline streak alive with a quick, secure sign-in.
            </p>

            <div className="auth-buttons">
              <a className="auth-provider" href={googleAuthUrl}>
                <span className="provider-icon google">G</span>
                Continue with Google
              </a>
              <a className="auth-provider" href={githubAuthUrl}>
                <span className="provider-icon github">GH</span>
                Continue with GitHub
              </a>
            </div>

            <div className="auth-divider">
              <span>or sign in with email</span>
            </div>

            <label className="auth-label" htmlFor="email">
              Email
            </label>
            <input
              className="auth-input"
              id="email"
              name="email"
              type="email"
              placeholder="you@college.edu"
            />
            <label className="auth-label" htmlFor="password">
              Password
            </label>
            <input
              className="auth-input"
              id="password"
              name="password"
              type="password"
              placeholder="********"
            />

            <div className="auth-row">
              <label className="auth-check">
                <input type="checkbox" defaultChecked />
                Keep me signed in
              </label>
              <button className="link-btn" type="button">
                Forgot password
              </button>
            </div>

            <button className="btn btn-solid auth-submit" type="button">
              Sign in
            </button>
            <p className="auth-switch">
              New here?{' '}
              <Link className="link-btn" to="/login">
                Create an account
              </Link>
            </p>
          </div>

          <div className="auth-side">
            <div className="auth-quote-card">
              <p className="auth-quote-title">What disciplined students say</p>
              <p className="auth-quote">
                &quot;GrowthHub turned my prep into a predictable timetable. I
                stopped guessing and started finishing.&quot;
              </p>
              <div className="auth-quote-footer">
                <strong>Riya Mahajan</strong>
                <span>Final year, CSE</span>
              </div>
              <div className="auth-controls">
                <button className="control-btn" type="button">
                  &lt;
                </button>
                <button className="control-btn" type="button">
                  &gt;
                </button>
              </div>
            </div>

            <div className="auth-mini-card">
              <p className="mini-title">Tonight&apos;s focus</p>
              <h3>3 blocks complete</h3>
              <p className="mini-subtitle">Efficiency at 86%</p>
              <div className="mini-pills">
                <span className="badge">DSA</span>
                <span className="badge">Mock interview</span>
                <span className="badge">Aptitude</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Landing />} />
        <Route path="/login" element={<Login />} />
        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
