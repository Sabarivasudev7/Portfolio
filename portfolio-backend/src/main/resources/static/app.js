const API_BASE_URL = "/api";

document.addEventListener("DOMContentLoaded", () => {
  if (window.lucide) lucide.createIcons();
  fetchSkills();
  fetchProjects();
  setupContactForm();
  setupChatWidget();
  setupPopupOutsideClick();
});

// ---------------- Skills Section ----------------
async function fetchSkills() {
  const container = document.getElementById("skills-grid");
  try {
    const response = await fetch(`${API_BASE_URL}/skills`);
    const skills = await response.json();

    // Group skills by category
    const grouped = {};
    skills.forEach(skill => {
      if (!grouped[skill.category]) grouped[skill.category] = [];
      grouped[skill.category].push(skill);
    });

    container.innerHTML = "";

    for (const category in grouped) {
      const heading = document.createElement("h3");
      heading.className = "sub-title";
      heading.textContent = category;
      container.appendChild(heading);

      const skillsList = document.createElement("div");
      skillsList.className = "skills-list";

      grouped[category].forEach(skill => {
        const card = document.createElement("div");
        card.className = "skill-card";
        card.innerHTML = `
          <div class="skill-label">
            <span>${skill.name}</span>
            <span class="skill-percent">${skill.percentage}%</span>
          </div>
          <div class="skill-bar-container">
            <div class="skill-bar-fill" style="width:${skill.percentage}%;"></div>
          </div>
        `;
        skillsList.appendChild(card);
      });

      container.appendChild(skillsList);
    }
  } catch (error) {
    console.error("Error loading skills:", error);
    container.innerHTML = `<p style="color: var(--text-muted)">Failed to load skills.</p>`;
  }
}

// ---------------- Projects Section ----------------
async function fetchProjects() {
  const container = document.getElementById("projects-grid");
  try {
    const response = await fetch(`${API_BASE_URL}/projects`);
    const projects = await response.json();

    container.innerHTML = projects.map(project => `
      <div class="glow-card">
        <h3>${project.title}</h3>
        <p>${project.description}</p>
        <h4 class="tech-title">Technologies Used:</h4>
        <div class="tech-list">
          ${project.technologies}
        </div>
      </div>
    `).join("");
  } catch (error) {
    console.error("Error loading projects:", error);
    container.innerHTML = `<p style="color: var(--text-muted)">Failed to load projects.</p>`;
  }
}

// ---------------- Contact Form ----------------
function setupContactForm() {
  const form = document.getElementById("contact-form");
  const status = document.getElementById("form-status");

  form.addEventListener("submit", async (e) => {
    e.preventDefault();
    const payload = {
      name: document.getElementById("name").value,
      email: document.getElementById("email").value,
      message: document.getElementById("message").value,
    };

    try {
      const response = await fetch(`${API_BASE_URL}/contact`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload),
      });

      status.textContent = response.ok ? "Message Sent Successfully!" : "Failed to send message.";
      if (response.ok) form.reset();
    } catch (error) {
      console.error("Contact submission error:", error);
      status.textContent = "Error submitting form.";
    }
  });
}

// ---------------- Chat Widget ----------------
function setupChatWidget() {
  const toggleBtn = document.getElementById("toggle-chat");
  const chatPopup = document.getElementById("chat-popup");
  const closeBtn = document.getElementById("close-chat");

  if (toggleBtn && chatPopup && closeBtn) {
    toggleBtn.addEventListener("click", () => chatPopup.classList.toggle("hidden"));
    closeBtn.addEventListener("click", () => chatPopup.classList.add("hidden"));
  }
}

// ---------------- Git + Resume ----------------
function openGit() {
  window.open("https://github.com/Sabarivasudev7", "_blank");
}

function openResumePopup() {
  const popup = document.getElementById("resume-popup");
  if (popup) popup.classList.remove("hidden");
}

function closeResumePopup() {
  const popup = document.getElementById("resume-popup");
  if (popup) popup.classList.add("hidden");
}

function downloadResume() {
  const resumeUrl = "/assets/Sabari_Vasudev_Resume.pdf"; 
  const link = document.createElement("a");
  link.href = resumeUrl;
  link.download = "Sabari_Vasudev_Resume.pdf";
  document.body.appendChild(link);
  link.click();
  document.body.removeChild(link);
  closeResumePopup();
}

function viewResume() {
  const resumeUrl = "/assets/Sabari_Vasudev_Resume.pdf";  
  window.open(resumeUrl, "_blank");
  closeResumePopup();
}

// Close popup when clicking outside the modal content card
function setupPopupOutsideClick() {
  window.addEventListener("click", (event) => {
    const popup = document.getElementById("resume-popup");
    if (event.target === popup) {
      closeResumePopup();
    }
  });
}

// ---------------- More About Me ----------------
function openAboutPage() {
  window.open("more-about-me.html", "_blank"); 
}